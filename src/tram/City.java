package streetcar;

class City {
	  private float a = 10;  /*width*/
	  private float b = 5;   /*Height*/
	  private float budget = 1000000 ;   /*city's budget in €*/
	  private float station_price = 10000;   /*price to build a station*/
	  private double surface = (Math.PI*a*b);  /*surface of the city*/

	  public float getA(){return a;}                                  /**/
	  public float getB(){return b;}                                  /**/
	  public float getBudget(){return budget;}                        /*definition of get methods*/
	  public float getStation_price(){return station_price;}          /**/
	  public double getSurface(){return surface;}                     /**/

}
