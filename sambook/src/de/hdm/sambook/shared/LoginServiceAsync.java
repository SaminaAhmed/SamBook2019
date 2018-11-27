package de.hdm.sambook.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.sambook.client.LoginInfo;

public interface LoginServiceAsync {
  public void login(String requestUri, AsyncCallback<LoginInfo> async);
}
