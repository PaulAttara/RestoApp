/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse223.resto.model;
import java.io.Serializable;

// line 104 "../../../../../RestoAppPersistence2.ump"
// line 19 "../../../../../RestoApp-v7.ump"
public class TakeOut implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TakeOut Attributes
  private int phoneNumber;
  private String takeOutName;

  //TakeOut Associations
  private Order order;
  private RestoApp restoApp;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TakeOut(int aPhoneNumber, String aTakeOutName, Order aOrder, RestoApp aRestoApp)
  {
    phoneNumber = aPhoneNumber;
    takeOutName = aTakeOutName;
    boolean didAddOrder = setOrder(aOrder);
    if (!didAddOrder)
    {
      throw new RuntimeException("Unable to create takeOut due to order");
    }
    boolean didAddRestoApp = setRestoApp(aRestoApp);
    if (!didAddRestoApp)
    {
      throw new RuntimeException("Unable to create takeOut due to restoApp");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPhoneNumber(int aPhoneNumber)
  {
    boolean wasSet = false;
    phoneNumber = aPhoneNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setTakeOutName(String aTakeOutName)
  {
    boolean wasSet = false;
    takeOutName = aTakeOutName;
    wasSet = true;
    return wasSet;
  }

  public int getPhoneNumber()
  {
    return phoneNumber;
  }

  public String getTakeOutName()
  {
    return takeOutName;
  }

  public Order getOrder()
  {
    return order;
  }

  public RestoApp getRestoApp()
  {
    return restoApp;
  }

  public boolean setOrder(Order aNewOrder)
  {
    boolean wasSet = false;
    if (aNewOrder == null)
    {
      //Unable to setOrder to null, as takeOut must always be associated to a order
      return wasSet;
    }
    
    TakeOut existingTakeOut = aNewOrder.getTakeOut();
    if (existingTakeOut != null && !equals(existingTakeOut))
    {
      //Unable to setOrder, the current order already has a takeOut, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Order anOldOrder = order;
    order = aNewOrder;
    order.setTakeOut(this);

    if (anOldOrder != null)
    {
      anOldOrder.setTakeOut(null);
    }
    wasSet = true;
    return wasSet;
  }

  public boolean setRestoApp(RestoApp aRestoApp)
  {
    boolean wasSet = false;
    if (aRestoApp == null)
    {
      return wasSet;
    }

    RestoApp existingRestoApp = restoApp;
    restoApp = aRestoApp;
    if (existingRestoApp != null && !existingRestoApp.equals(aRestoApp))
    {
      existingRestoApp.removeTakeOut(this);
    }
    restoApp.addTakeOut(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Order existingOrder = order;
    order = null;
    if (existingOrder != null)
    {
      existingOrder.setTakeOut(null);
    }
    RestoApp placeholderRestoApp = restoApp;
    this.restoApp = null;
    if(placeholderRestoApp != null)
    {
      placeholderRestoApp.removeTakeOut(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "phoneNumber" + ":" + getPhoneNumber()+ "," +
            "takeOutName" + ":" + getTakeOutName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "order = "+(getOrder()!=null?Integer.toHexString(System.identityHashCode(getOrder())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "restoApp = "+(getRestoApp()!=null?Integer.toHexString(System.identityHashCode(getRestoApp())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 107 "../../../../../RestoAppPersistence2.ump"
  private static final long serialVersionUID = 696913933552499839L ;

  
}