import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import {Customer} from '../customer';
import {CustomerService} from '../services/customer.service';

@Component({
  selector: 'app-customer-details',
  templateUrl: './customer-details.component.html',
  styleUrls: ['./customer-details.component.css']
})
export class CustomerDetailsComponent implements OnInit {

 
  id: number | any;
  customer: Customer | any;

  constructor(private route: ActivatedRoute,
    private router: Router,
    private customerService: CustomerService) { }

  ngOnInit() {
    this.customer = new Customer();

    this.id = this.route.snapshot.params['id'];
    console.log("customer id" +this.id);
    this.customerService.getCustomer(this.id).subscribe(data => {
      console.log(data);
      this.customer = data;
    }, error => console.log(error) );
  }

  list() {
    this.router.navigate(['customerList']);
  }

}
