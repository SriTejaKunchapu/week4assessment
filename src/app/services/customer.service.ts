import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Customer } from '../customer';

@Injectable({
    providedIn:'root'
})
export class CustomerService{

    private baseUrl = 'http://localhost:8091/api/customer';

    private baseUrlConsumer  = 'http://localhost:8764/consumer/customer';


    constructor(private http: HttpClient) { }


    getCustomer(id: number): Observable<any> {
        return this.http.get(`${this.baseUrl}/${id}`);
      }

      getCustomerList(): Observable<any> {
        return this.http.get(`${this.baseUrl}`);
      }

      getAllCustomerList(): Observable<any> {
        return this.http.get(`${this.baseUrlConsumer}`);
      }

      updateCustomer(id: number, value: any): Observable<Object> {
        return this.http.put(`${this.baseUrl}/${id}`, value);
      }

    addCustomer(customer: Object): Observable<Object> {
        return this.http.post(`${this.baseUrl}`, customer);
      }


      findByName(name: string): Observable<Customer[]> {
        return this.http.get<Customer[]>(`${this.baseUrl}?=${name}`);
      }
    

      deleteCustomer(id: number): Observable<any> {
        return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
      }


}