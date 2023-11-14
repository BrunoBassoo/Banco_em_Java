package ControllerCliente;
import DB.DB_Cliente;
import DB.conexao_banco;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Cliente;
import views.Debito;
/**
 *
 * @author T-Gamer
 */
public class ControllerDebito {
    private Debito view;

    public ControllerDebito(Debito view) {
        this.view = view;
    }
    public void debitar(){
        String CPF = view.getTxtCpfCliente().getText();
        String senha = view.getTxtSenhaCliente().getText();
        double valor_debito = Double.parseDouble(view.getEntrada_saldo().getText());

        Cliente cliente = new Cliente(CPF,senha);
        conexao_banco conexao = new conexao_banco();
        try{
            Connection conn = conexao.getConnection();
            DB_Cliente db = new DB_Cliente(conn);
            db.debitar(cliente,valor_debito);
            JOptionPane.showMessageDialog(view,"Debito efetuado!","alerta",JOptionPane.INFORMATION_MESSAGE );
        } catch( SQLException e){
            JOptionPane.showMessageDialog(view, "Erro de conexão, tente novamente!", "Aviso", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
        }
    }
}
