package Presentacion.Controller;

public abstract class ApplicationController {
	
	private static ApplicationController instance;

	public static ApplicationController getInstance() {
		if(instance == null){
			instance = new ApplicationControllerImp();
		}
		return instance;
	}

	public void setInstance(ApplicationController instance) {
		this.instance = instance;
	}

	public abstract void handleRequest(Context resquest);
}