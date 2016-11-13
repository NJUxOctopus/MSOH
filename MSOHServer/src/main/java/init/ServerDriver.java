/**
 * created by zqh on 2016-11-13
 */
package init;

import rmi.RemoteHelper;

public class ServerDriver {

	public ServerDriver(){
		new RemoteHelper();
	}

	public static void main(String[] args) {
		new ServerDriver();
	}

}
