package streetcar;

class Station {
	  City burger = new City();
	  float nb_station = (burger.getBudget()/burger.getStation_price()) ; /*number of tram stations in the city*/
	  double r= (Math.sqrt(burger.getSurface()/(Math.PI*nb_station))); /*distance between two stations*/
	  float xs[]=new float[(int)nb_station];     /*tab of x coordinates*/
	  float ys[]=new float[(int)nb_station];     /*tab of y coordiantes*/

	  public void create_network(){
	      float x = 0;
	      float y = - burger.getB();
	      for (int i=0 ; i< nb_station; i++){
	        if (x<= burger.getA()*Math.sqrt(1-((y*y)/(burger.getB()*burger.getB()))))   {
	                xs[i]=x;
	                ys[i]=y;
	        }
	        else {
	          x = -1*(float)(burger.getA()*Math.sqrt(1-((y*y)/(burger.getB()*burger.getB()))));
	          y=y+(float)r*(float)1.5;
	        }
	        x=x+(float)r*(float)1.5;

	        System.out.println(x);
	      }

	  }
}
