package model.dao;

public class DaoFactory {

	public static IPedidoDao createPedidoDao() {
		return new PedidoDaoJDBC();
	}

	public static IClienteDao createClienteDao() {
		return new ClienteDaoJDBC();
	}

	public static IComidaDao createComidaDao() {
		return new ComidaDaoJDBC();
	}

	public static IBebidaDao createBebidaDao() {
		return new BebidaDaoJDBC();
	}

}
