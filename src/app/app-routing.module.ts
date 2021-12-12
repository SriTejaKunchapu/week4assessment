import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddCustomerComponent } from './add-customer/add-customer.component';
import { CustomerDetailsComponent } from './customer-details/customer-details.component';
import { CustomerListComponent } from './customer-list/customer-list.component';
import { UpdateCustomerComponent } from './update-customer/update-customer.component';

const routes: Routes = [

  { path: '', redirectTo: 'customer', pathMatch: 'full' },
  {path:"add",component:AddCustomerComponent},
  {path:"customerList",component:CustomerListComponent},
  { path: "details/:id", component: CustomerDetailsComponent },
   { path: "update/:id", component: UpdateCustomerComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
