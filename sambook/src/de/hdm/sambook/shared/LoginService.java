package de.hdm.sambook.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.sambook.client.LoginInfo;

@RemoteServiceRelativePath("loginservice")
public interface LoginService extends RemoteService {
  public LoginInfo login(String requestUri);
}
