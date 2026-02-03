import Telefonia.TelefoneNativo;
import Navegadores.Safari;
import Midia.Reprodutor;

public class App {
    public static void main(String[] args) throws Exception {
        Reprodutor appReprodutor = new Reprodutor();
        TelefoneNativo appLigacao = new TelefoneNativo();
        Safari appSafari = new Safari();

        appLigacao.ligar(12304);
    }
}
