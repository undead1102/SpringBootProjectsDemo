package org.hanbo.boot.rest.models;

public class CarRequestModel
{
   private int year;
   
   private String manufacturer;
   
   private String model;

   public int getYear()
   {
      return year;
   }

   public void setYear(int year)
   {
      this.year = year;
   }

   public String getManufacturer()
   {
      return manufacturer;
   }

   public void setManufacturer(String manufacturer)
   {
      this.manufacturer = manufacturer;
   }

   public String getModel()
   {
      return model;
   }

   public void setModel(String model)
   {
      this.model = model;
   }
}
