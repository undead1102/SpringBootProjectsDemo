package org.hanbo.boot.rest.controllers;

import org.hanbo.boot.rest.models.CarRequestModel;
import org.hanbo.boot.rest.models.PurchaseTaxModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleSecureController
{
   @PreAuthorize("hasRole('ROLE_USER')")
   @ResponseBody
   @RequestMapping(value="/secure/calculateTax", method = RequestMethod.POST)
   public ResponseEntity<PurchaseTaxModel> calculateTax(
      @RequestBody
      CarRequestModel req)
   {
      PurchaseTaxModel retVal = new PurchaseTaxModel();
      retVal.setYear(req.getYear());
      retVal.setModel(req.getModel());
      retVal.setManufacturer(req.getManufacturer());
      retVal.setTaxAmount(4000);
      retVal.setTaxRate(0.03f);
      
      ResponseEntity<PurchaseTaxModel> resp = ResponseEntity.ok(retVal);
      return resp;
   }
}
