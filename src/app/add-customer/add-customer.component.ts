import { Component, OnInit } from '@angular/core';
import {FormGroup, FormControl, Validator, FormBuilder} from '@angular/forms'
import { Customer } from '../customer';
import { Router } from '@angular/router';
import { CustomerService } from '../services/customer.service';
@Component({
  selector: 'app-add-customer',
  templateUrl: './add-customer.component.html',
  styleUrls: ['./add-customer.component.css']
})
export class AddCustomerComponent implements OnInit {

  customer: Customer  = new Customer();
  submitted=false;
  constructor(private customerService: CustomerService,
    private router: Router) { }

  ngOnInit(): void {
  }

  newCustomer(): void{
    this.submitted=false;
    this.customer = new Customer();
  }


  save(){
    this.customerService.addCustomer(this.customer).subscribe(data =>{
      console.log(data);
      this.customer = new Customer();
    },
    error => console.log(error));
  }

  onSubmit(){
    this.submitted=true;
    this.save();
  }

}
