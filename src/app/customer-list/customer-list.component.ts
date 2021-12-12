import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Customer } from '../customer';
import { CustomerService } from '../services/customer.service';

@Component({
  selector: 'app-customer-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.css']
})
export class CustomerListComponent implements OnInit {

  customerList : Observable<Customer[]> | any ;


  constructor(private customerService: CustomerService, private router: Router) { }

  ngOnInit(): void {
    this.reloadData();
  }

  reloadData() {
  this.customerList= this.customerService.getAllCustomerList();
  }


  deleteCustomers(id: number) {
   this.customerService.deleteCustomer(id).subscribe(
     data =>{
       console.log(data);
       this.reloadData();
     }  
   )
  }


  CustomerDetails(id: number){
    this.router.navigate(['details', id]);
  }

  updateCustomer(id: number){
    this.router.navigate(['update', id]);
  }
  
}
