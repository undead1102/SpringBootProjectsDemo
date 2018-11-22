package org.hanbo.boot.rest.models;

public class PurchaseTaxModel extends CarRequestModel
{
   private float taxRate;
   
   private float taxAmount;

   public float getTaxRate()
   {
      return taxRate;
   }

   public void setTaxRate(float taxRate)
   {
      this.taxRate = taxRate;
   }

   public float getTaxAmount()
   {
      return taxAmount;
   }

   public void setTaxAmount(float taxAmount)
   {
      this.taxAmount = taxAmount;
   }
}
