import java.util.*;

public class katanplayer {
	
	public kata kat = new kata();

	public int id;

	public String name;

	public List<Integer> goods = new ArrayList<>();

	public List<Integer> sHome = new ArrayList<>();

	public List<Integer> fields = new ArrayList<>();

	
	
	public katanplayer(int id, String name) {
						//    0    1     2      3
		//initialize goods {wood,starch,pilos,sheap}
		for(int i = 0; i < 4; i++) {
			goods.add(0);
		}
		//player.d != 0 h 1 
		this.id = id;
		this.name = name;
	}


	/*i einai ta xwrafia j einai oi korifes
	 *pairnei mia koryfh kai vriskh ta fields pu antistixun*/
	public void findField(int koryfh) {
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				if(i < 3 && kat.board[i][j] == koryfh) {
					
					fields.add(i);

				} else if (i < 6 && kat.board[i][j] == koryfh) {

					fields.add(i);

				}
			}
		}
		
	}

	//kanei add 1 sHome me "koryfh" kai dinei ta antistoixa fields
	public void add_sHome(int koryfh) {
		
		
		
		findField(koryfh);
	}




	
}