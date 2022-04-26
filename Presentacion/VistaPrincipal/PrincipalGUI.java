package Presentacion.vistaPrincipal;

public abstract class PrincipalGUI implements GUI {
	
	private static PrincipalGUI instancia;

	public static synchronized PrincipalGUI getInstancia() {
		if(instancia == null){
			instancia = new PrincipalGUIImp();
		}
		return instancia; 
	}

	public void setInstancia(PrincipalGUI instancia) {
		PrincipalGUI.instancia = instancia;
	}

}