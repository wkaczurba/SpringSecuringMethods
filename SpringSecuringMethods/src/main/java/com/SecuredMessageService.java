package com;

import org.springframework.security.access.annotation.Secured;

import com.domain.Message;

public class SecuredMessageService implements MessageService {

	@Override
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public void sendMessage(Message msg) {
		//if (msg.getFrom().getUserName().equals(anObject))
		
		System.out.println("securedFunction executing...");
	}
}

/*public class SecuredSpittleService implements SpittleService {

  @Secured({"ROLE_SPITTER", "ROLE_ADMIN"})
  public void addSpittle(Spittle spittle) {
    System.out.println("Method was called successfully");
  }
  
}*/
