package view;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Pedido;
import model.dao.BebidaDaoJDBC;
import model.dao.ClienteDaoJDBC;
import model.dao.ComidaDaoJDBC;
import model.dao.DaoFactory;
import model.dao.PedidoDaoJDBC;

public class ConsultaViewController implements Initializable {

	ClienteDaoJDBC clienteDao = null;
	ComidaDaoJDBC comidaDao = null;
	BebidaDaoJDBC bebidaDao = null;
	PedidoDaoJDBC pedidoDao = null;

	@FXML
	private TableView<Pedido> tblPedidos;

	@FXML
	private TextField tfCliente;

	@FXML
	private TableColumn<Pedido, String> clPedido;

	@FXML
	private Button btnBuscar;

	@FXML
	private Button btnVoltar;

	@FXML
	private void onBtnBuscar() {
		System.out.println("Botão Buscar teste");

	}

	@FXML
	private void onBtnVoltar() {
		System.out.println("Botão Voltar teste");
		Main.trocaTela("main",null);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		clienteDao = (ClienteDaoJDBC) DaoFactory.createClienteDao();
		comidaDao = (ComidaDaoJDBC) DaoFactory.createComidaDao();
		bebidaDao = (BebidaDaoJDBC) DaoFactory.createBebidaDao();
		pedidoDao = (PedidoDaoJDBC) DaoFactory.createPedidoDao();

	}

}
