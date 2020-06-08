package view;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import application.Main;
import application.Main.MudancaTela;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.Bebida;
import model.Cliente;
import model.Comida;
import model.Pedido;
import model.dao.BebidaDaoJDBC;
import model.dao.ClienteDaoJDBC;
import model.dao.ComidaDaoJDBC;
import model.dao.DaoFactory;
import model.dao.PedidoDaoJDBC;

public class PedidoViewController implements Initializable {

	ClienteDaoJDBC clienteDao = null;
	ComidaDaoJDBC comidaDao = null;
	BebidaDaoJDBC bebidaDao = null;
	PedidoDaoJDBC pedidoDao = null;

	@FXML
	private TextField tfCliente;

	@FXML
	private TextField tfNomeDoPrato;

	@FXML
	private TextField tfObservacoesComida;

	@FXML
	private TextField tfTipoDaBebida;

	@FXML
	private TextField tfEspecificacoes;

	@FXML
	private DatePicker dpData;

	@FXML
	private Button btnOk;

	@FXML
	private Button btnCancelar;

	@FXML
	private void onBtnok() {
		System.out.println("Botão ok ");

		try {

			if (tfCliente.getText().isEmpty()) {
				throw new RuntimeException("Campo nome é obrigatório");
			}
			if (tfNomeDoPrato.getText().isEmpty()) {
				throw new RuntimeException("Campo endereço é obrigatório");
			}
			if (tfTipoDaBebida.getText().isEmpty()) {
				throw new RuntimeException("Campo valor é obrigatório");
			}

			if (tfEspecificacoes.getText().isEmpty()) {
				throw new RuntimeException("Campo valor é obrigatório");
			}

			String nomeCliente = tfCliente.getText();
			String nomeDoPrato = tfNomeDoPrato.getText();
			String observacoesComida = tfObservacoesComida.getText();
			String tipoDaBebida = tfTipoDaBebida.getText();
			String especificacoes = tfEspecificacoes.getText();
			LocalDate localDate = dpData.getValue();
			Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
			Date data = Date.from(instant);

			Cliente cliente = new Cliente();
			cliente.setNome(nomeCliente);

			Comida comida = new Comida();
			comida.setNomePrato(nomeDoPrato);
			comida.setObsevacoes(observacoesComida);

			Bebida bebida = new Bebida();
			bebida.setTipoBebida(tipoDaBebida);
			bebida.setEspecificacoes(especificacoes);

			Pedido pedido = new Pedido();
			pedido.setData(data);

			int id_cliente = clienteDao.insert(cliente);
			cliente.setIdCliente(id_cliente);

			pedido.setCliente(cliente);

			int id_comida = comidaDao.insert(comida);
			comida.setIdComida(id_comida);

			pedido.setComida(comida);

			int id_bebida = bebidaDao.insert(bebida);
			bebida.setIdBebida(id_bebida);

			pedido.setBebida(bebida);

			int id_pedido = pedidoDao.insert(pedido);
			pedido.setIdPedido(id_pedido);

			pedido.setIdPedido(id_pedido);

			pedidoDao.insert(pedido);

			Main.trocaTela("main", null);

		} catch (RuntimeException e) {

			Alert alerta = new Alert(Alert.AlertType.ERROR);
			alerta.setTitle("Erro");
			alerta.setHeaderText("Erro no formulário");
			alerta.setContentText(e.getMessage());
			alerta.showAndWait();

		}

	}

	@FXML
	private void onBtnCancelar() {
		System.out.println("Botão Cancelar teste");
		Main.trocaTela("main", null);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		MudancaTela novoListener = new MudancaTela() {

			@Override
			public void mudarTelaListener(String tela, Object obj) {

				if (tela.equals("cadastro")) { // garantir que o evento é na tela cadastro

					System.out.println("tela: " + tela + " dado " + obj);
				}

			}
		};

		Main.addListenerMudancaTela(novoListener);

		clienteDao = (ClienteDaoJDBC) DaoFactory.createClienteDao();
		comidaDao = (ComidaDaoJDBC) DaoFactory.createComidaDao();
		bebidaDao = (BebidaDaoJDBC) DaoFactory.createBebidaDao();
		pedidoDao = (PedidoDaoJDBC) DaoFactory.createPedidoDao();

	}

}
