package Integracion.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Integracion.Transaction.TransactionManager;
import Negocio.Factura.TFacturaTienda;

public class MostrarFacturaConMayorPorcentajeDescuento implements Query{

	public Object execute(Object parametro) {
		List<TFacturaTienda> lista = new ArrayList<TFacturaTienda>();
		Connection conexion;
		PreparedStatement pStatement;
		ResultSet rs;
		
		try {
			conexion = (Connection) TransactionManager.getInstancia().getTransaccion().getResource();
			// MIRAR LA CONSULTA
			pStatement = conexion.prepareStatement("SELECT f.*, d.porcentaje FROM factura_tienda f JOIN descuento d ON (f.id_descuento = d.id) WHERE d.activo = true");
			rs = pStatement.executeQuery();
			int porcentaje = 0;
			while (rs.next()) {
				int p = rs.getInt("porcentaje");
				if (porcentaje < p) {
					porcentaje = p;
					lista.clear();
				}
				if (porcentaje == p) {
					lista.add(new TFacturaTienda(rs.getInt("id"), rs.getDate("fecha"), rs.getDouble("preciototal")));
				}
			}
			pStatement.close();
			rs.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lista;
	}

}
