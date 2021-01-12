import { WorkDayDto } from './workDayDto';
import { WorkDay } from './workDay';

export interface Shelter {
        username:string;
        password:string;
        phoneNumber:string;
        roles:string;
        email:string;
        address:string;
        active:boolean;
        shelterCode:string;
        description:string;
        workDay: WorkDayDto
}