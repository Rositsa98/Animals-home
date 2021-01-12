import { Gender } from './gender';
import { PetType } from './petType';

export class PetDetailsDto {
     petName: string;
     breed: string;
     color: string;
     city: string;
     age: number;
     months: number;
     gender: Gender;
     weight: number;
     petType: PetType;

     public setPetName(petName: string) {
          this.petName = petName;
     }

     public setBreed(breed: string) {
          this.breed = breed;
     }

     public setColor(color: string) {
          this.color = color;
     }

     public setCity(city: string) {
          this.city = city;
     }

     public setAge(age: number) {
          this.age = age;
     }

     public setMonths(months: number) {
          this.months = months;
     }

     public setGender(gender: Gender) {
          this.gender = gender;
     }

     public setWeight(weight: number) {
          this.weight = weight;
     }

     public setPetType(petType: PetType) {
          this.petType = petType;
     }
}