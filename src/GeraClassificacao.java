import java.util.Scanner;

public class GeraClassificacao {

    public void geraClassificacao(int conteudo) {
        
        for (int estrela = 1; estrela < 10; estrela++) {
          if (estrela > conteudo) {
            break;
          } else {
            System.out.print("\u001b[40m\u2B50\u001b[0m");
          }
        }
        System.out.println();

        System.out.printf("\u001b[1mAvalie de 0 a 10: \u001b[0m");
        Scanner avaliacao = new Scanner(System.in); 
          int av = avaliacao.nextInt();
          System.out.println("\u001b[1m\u001b[30m \u001b[44m Avaliação do usuário: \u001b[0m");
          for (int f = 1; f < 10; f++) {
            if (f > av) {
              break;
            } else {
              System.out.print("\u001b[40m\u2B50\u001b[0m");
            }
          }
        
        System.out.println();
    }
    
}
