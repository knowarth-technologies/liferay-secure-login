package secure.login.web.service.application;

import com.google.gson.Gson;
import com.knowarth.security.modal.SecureLoginMobData;
import com.knowarth.security.model.SecureLogin;
import com.knowarth.security.service.SecureLoginLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.Collections;
import java.util.Date;
import java.util.Set;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;

import org.apache.commons.codec.binary.Base64;
import org.osgi.service.component.annotations.Component;

/**
 * @author darshan.gandhi
 */
@ApplicationPath("/secureloginservices")
@Component(immediate = true, service = Application.class)
public class SecureLoginWebServiceApplication extends Application {
	private static final Log LOG = LogFactoryUtil.getLog(SecureLoginWebServiceApplication.class);
	public static final int NO_OF_SECOND_QR_TOKEN_IS_VALID=60;
	
	public Set<Object> getSingletons() {
		return Collections.<Object>singleton(this);
	}
	
	@POST
	@Path("/sendqrcode")
	@Produces("application/json")
	public String sendQRCode(String mobileData) {
		Gson gson =  new Gson();
		SecureLoginMobData secureLoginMobData = gson.fromJson(mobileData,SecureLoginMobData.class);
		long companyId = PortalUtil.getDefaultCompanyId();
		JSONObject json = JSONFactoryUtil.createJSONObject();
		
		if(Validator.isNotNull(secureLoginMobData)){
			if(Validator.isNotNull(secureLoginMobData.getEncodedTokenFromMob()) && Validator.isNotNull(secureLoginMobData.getEmailAddress())){
				SecureLogin secureLogin = SecureLoginLocalServiceUtil.getSecureLoginByEmailAddress(companyId,secureLoginMobData.getEmailAddress());
				if(Validator.isNotNull(secureLogin)){
					String encodedQRCodeFromMob = secureLoginMobData.getEncodedTokenFromMob();
					secureLogin.setTokenValidated(getEncodedString(encodedQRCodeFromMob, secureLogin));
					Date currentDate = new Date();
					Date qrCodeTokenDate = secureLogin.getQrCodeTokenDate(); 
					
					int secondElapsedAfterQRCodeGenerate = 0;
					if(Validator.isNotNull(qrCodeTokenDate)){
						secondElapsedAfterQRCodeGenerate =(int)(currentDate.getTime() - qrCodeTokenDate.getTime())/1000;
					}
					
					if(secondElapsedAfterQRCodeGenerate < NO_OF_SECOND_QR_TOKEN_IS_VALID){
						boolean decodedQRCodesaved=false;
						try {
							SecureLoginLocalServiceUtil.updateSecureLogin(secureLogin);
							decodedQRCodesaved=true;
						} catch (SystemException e) {
							LOG.error("Error occured while updating secure loing value - isTokenValidatedFromMob flag from web service "+e);
						}
						if(decodedQRCodesaved == true){
							if(Boolean.TRUE.toString().equalsIgnoreCase(secureLogin.getTokenValidated())){
									LOG.info("Token is validated");
									json.put("message","You are sucessfully login");
							}else{
								json.put("message","Secure login token is not valid");
								LOG.error("Secure login token is not valid");
							}
						}else{
							json.put("message","Decoded QR code is not saved into system. Error is occured on server");
							LOG.error("Decoded QR code is not saved into system.");
						}
					}else{
						json.put("message","Your token is expired. Please re-genreate QR code and scan it again");
						LOG.error("TOken is Expired");
					}
					
				}else{
					json.put("message","Your Registartion key is not valid");
					LOG.error("Registartion key is not valid / mising");
				}
			}else{
				json.put("message","Your Registartion key is not valid");
				LOG.error("Registartion key is not valid / mising");
			}
		}else{
			json.put("message","No data is sent from device");
			LOG.error("No data is sent from device");
		}
		
		return json.toString();
	}
	
	/**
	 * This method will encode the QR code with key (randomToken+registrationKey) and compare it with the string came from Mobile.If equals, returns true.
	 * @param inputStrFromMob
	 * @param secureLogin
	 * @return String - if login is valid.
	 */
	private String getEncodedString(String inputStrFromMob,SecureLogin secureLogin){
		boolean isValidLogin = false;
		String encodedString = StringPool.BLANK;
		
		if(Validator.isNotNull(secureLogin) && Validator.isNotNull(secureLogin.getRegistrationKey())){
			try {
				Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
				String secret = secureLogin.getEncodeToken()+secureLogin.getRegistrationKey();

				SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
				sha256_HMAC.init(secret_key);

				if(Validator.isNotNull(secureLogin.getQrCodeToken().getBytes())){
					encodedString = Base64.encodeBase64String(sha256_HMAC.doFinal(secureLogin.getQrCodeToken().getBytes()));
				}
			}
			catch (Exception e){
				LOG.error("error occured while encoding string with SHA256 ");
			}
		}
		
		if(Validator.isNotNull(inputStrFromMob) && Validator.isNotNull(encodedString)){
			if(inputStrFromMob.equalsIgnoreCase(encodedString)){
				isValidLogin = true;
			}
		}
		return Boolean.valueOf(isValidLogin).toString();
	}

}