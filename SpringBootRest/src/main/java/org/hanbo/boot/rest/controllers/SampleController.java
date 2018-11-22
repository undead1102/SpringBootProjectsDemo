package org.hanbo.boot.rest.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hanbo.boot.rest.models.CarModel;
import org.hanbo.boot.rest.models.CarRequestModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController
{
   private List<CarModel> allCars;
   
   public SampleController()
   {
      allCars = createCarModelCollection();
   }
   
   @RequestMapping(value="/public/allCars/{yearOfManufacture}", method = RequestMethod.GET)
   public ResponseEntity<List<CarModel>> allCars(
      @PathVariable("yearOfManufacture")  
      int year)
   {
      ResponseEntity<List<CarModel>> retVal = null;
      if (allCars == null || year < 1980)
      {
         List<CarModel> listOfCars = new ArrayList<CarModel>();
         retVal = ResponseEntity.ok(listOfCars);
         return retVal;
      }
      
      List<CarModel> foundCars = allCars
         .stream()
         .filter(x -> x.getYearOfManufacturing() == year)
         .collect(Collectors.toList());
      retVal = ResponseEntity.ok(foundCars);
      return retVal;
   }
   
   @ResponseBody
   @RequestMapping(value="/public/findCar", method = RequestMethod.POST)
   public ResponseEntity<CarModel> findCar(
      @RequestBody
      CarRequestModel req)
   {
      ResponseEntity<CarModel> retVal = null;
      if (allCars == null || req == null)
      {
         retVal = ResponseEntity.ok(null);
         return retVal;
      }
      
      Optional<CarModel> foundCar =  allCars
         .stream()
         .filter(x -> x.getYearOfManufacturing() == req.getYear()
            && x.getMaker().equalsIgnoreCase(req.getManufacturer())
            && x.getModel().equalsIgnoreCase(req.getModel()))
         .findFirst();
      if (foundCar.isPresent())
      {
         retVal = ResponseEntity.ok(foundCar.get());
      }
      else
      {
         retVal = ResponseEntity.ok((CarModel)null);         
      }
      
      return retVal;
   }
   
   private List<CarModel> createCarModelCollection()
   {
      List<CarModel> retVal = new ArrayList<CarModel>();
      
      CarModel car = new CarModel();
      car.setFullPrice(20000);
      car.setMaker("Nessan");
      car.setModel("Altima");
      car.setRebateAmount(600);
      car.setSuggestedRetailPrice(19250);
      car.setYearOfManufacturing(2005);
      
      retVal.add(car);

      car = new CarModel();
      car.setFullPrice(20096);
      car.setMaker("Subaru");
      car.setModel("Legacy");
      car.setRebateAmount(487);
      car.setSuggestedRetailPrice(20001);
      car.setYearOfManufacturing(2006);

      retVal.add(car);
      
      car = new CarModel();
      car.setFullPrice(20890);
      car.setMaker("Subaru");
      car.setModel("Outback");
      car.setRebateAmount(695);
      car.setSuggestedRetailPrice(19980);
      car.setYearOfManufacturing(2007);

      retVal.add(car);
      
      car = new CarModel();
      car.setFullPrice(21500);
      car.setMaker("Honda");
      car.setModel("Civic");
      car.setRebateAmount(750);
      car.setSuggestedRetailPrice(20100);
      car.setYearOfManufacturing(2008);

      retVal.add(car);
      
      car = new CarModel();
      car.setFullPrice(22600);
      car.setMaker("Toyota");
      car.setModel("Camery");
      car.setRebateAmount(708);
      car.setSuggestedRetailPrice(21100);
      car.setYearOfManufacturing(2008);

      retVal.add(car);
      
      return retVal;
   }
}
