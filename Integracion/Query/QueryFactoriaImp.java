package Integracion.Query;

public class QueryFactoriaImp extends QueryFactoria {

    public Query nuevaQuery(String nombre) {
		
		if(nombre == "mostrarFacturaConMayorPorcentajeDescuento") return new MostrarFacturaConMayorPorcentajeDescuento();
		else if(nombre == "mostrarProductoMasComprado") return new MostrarProductoMasComprado();
		else if(nombre == "mostrarProductoPorNombreFabricante") return new MostrarProductoPorNombreFabricante();

		return null;
	}
}
