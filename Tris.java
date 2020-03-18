import java.util.*;
//creiamo un simil tris ovvero il funzionamento che dovrebbe avere
//la richiesta è una matrice 3x3 che dovrà essere riempita da 2 players
//e dovremo controllare ad ogni inserimento se vi è una tripla(un vincitore)

public class Tris {
  //Creo una matrice 3*3 vuota
  private static final int rows = 3;
  private static final int columns = 3;
  private int[][] matrix;
  private static int giocate = 0;
  private boolean endTurn = false;//controllo se ha inserito realmente

  public Tris() {
    matrix = new int[rows][columns];
    for(int i=0; i<rows; i++)
      for(int j =0; j<columns; j++){
          matrix[i][j] = -1; //inserisco -1 per indicare le posizioni libere
      }

  }

  // Metodo che stampa la matrice 
  public void printMatrix() {
    for(int i=0; i<rows; i++){
        for (int j=0; j<columns ; j++){
            System.out.print(this.matrix[i][j] + " ");
        }
        System.out.println(); // vado a capo dopo ogni riga per avere una visione migliore della matrice 
      }                  
  }
  

  //Metodo per giocare i player saranno 0 e 1
  public void play(int i, int j, int player) {

      if(matrix[i][j] != -1){
        System.out.println("Questa posizione è già occupata");
        this.endTurn = false;
      }
      else{
        matrix[i][j] = player;
        giocate++;
        this.endTurn = true;
      }

  }

  //ogni volta che inserisco un elemento devo verificare se vi è un tris nella matrice
  public boolean isTris(int riga, int colonna) {
    int count1 = 0;
    int count2 = 0;
    
        for (int j=0; j<columns ; j++) {
          if(this.matrix[riga][j]== 0) {
            count1++;
            if(count1==3){ 
                System.out.println(" Il giocatore 0 ha fatto tris ");
                return true;
            }
          }
          else {
             if(this.matrix[riga][j] == 1) {
                count2++;
                if(count2==3){ 
                    System.out.println(" Il giocatore 1 ha fatto tris ");
                    return true; 
                }
             }
          }
        }
      count1=0;
      count2=0;
      for (int j=0; j<rows ; j++) {
          if(this.matrix[j][colonna] == 0) {
            count1++;
            if(count1==3){ 
                System.out.println(" Il giocatore 0 ha fatto tris ");
                return true;
            }
          }
          else{
             if(this.matrix[j][colonna] == 1){
                count2++;
                if(count2==3){ 
                    System.out.println(" Il giocatore 1 ha fatto tris ");
                    return true;
                }
             }
          }
       }
    count1=0;
    count2=0;
    if(colonna == riga){
      for(int i=0; i<rows; i++){
          if(this.matrix[i][i] == 0){
              count1++;
              if(count1==3){ 
                  System.out.println(" Il giocatore 0 ha fatto tris ");
                  return true;
              }
          }
          else{
            if(this.matrix[i][i] == 1){
                count2++;
                if(count2==3){ 
                  System.out.println(" Il giocatore 1 ha fatto tris ");
                  return true;
                }
            }
          }
      }
      count1=0;
      count2=0;
      for(int i=2; i>=0; i--){
        if(this.matrix[i][i]== 0){
          count1++;
          if(count1==3){ 
              System.out.println(" Il giocatore 0 ha fatto tris ");
              return true;
          }
        } 
        else{
         if(this.matrix[i][i] == 1){
            count2++;
            if(count2==3){ 
              System.out.println(" Il giocatore 1 ha fatto tris ");
              return true;
             }
          } 
        }

      }
    }
    
    return false;
}

  public static void main(String[] args){
    Random player = new Random();
    player.setSeed(System.currentTimeMillis());
    //player.nextInt(2);// 0 incluso 2 escluso da javadoc, primo giocatore random ad ogni partita
    int playerCorrente = (int)player.nextInt(2);// 0 incluso 2 escluso da javadoc, primo giocatore random ad ogni partita;
    System.out.println("Primo giocatore: " + playerCorrente);
    Tris game = new Tris();
    Scanner in = new Scanner(System.in);
    //inizio a giocare
    while(true){

            System.out.print("inserisci riga per " + playerCorrente);
            System.out.println(" : ");
            int riga = in.nextInt();
            if (riga < 0 || riga > 2) return;
            System.out.print("Inserisci colonna per " + playerCorrente);
            System.out.println(" : ");
            int colonna = in.nextInt();
            if (colonna < 0 || colonna > 2) return;
            // Inserimento giocata
            game.play(riga,colonna,playerCorrente);

            // Visualizzo lo stato del tris
            game.printMatrix();

            // Verifico se è presente un vincitore
            boolean win = game.isTris(riga, colonna);
            if(win==true) return;

            if(giocate==9){
              //Se il tris è pieno e non c'è vincitore, la partita termina
              System.out.println("NESSUN VINCITORE");
              return;
            }
            // Se ha giocato 1 allora dopo gioca O, altrimenti viceversa
            if(game.endTurn == true){
              if(playerCorrente==1) playerCorrente=0;
              else playerCorrente=1;
            }
        }
    
        
  }



}
