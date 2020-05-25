import { WorkDayDto } from './workDayDto';

export class UserInfo {
    username: string;
    address: string;
    email: string;
    phoneNumber: string;
    roles: string;
    workDayDto: WorkDayDto;
    
    constructor()
    constructor(username?: string, address?: string, email?: string, phoneNumber?: string, roles?: string, workDayDto?: WorkDayDto) {
        this.username = username;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
        this.workDayDto = workDayDto;
    }

    public setUsername(username: string) {
        this.username = username;
    }

    public setAddress(address: string) {
        this.address = address;
    }

    public setEmail(email: string) {
        this.email = email;
    }

    public setPhoneNumber(phoneNumber: string) {
        this.phoneNumber = phoneNumber;
    }

    public setRoles(roles: string) {
        this.roles = roles;
    }

    public setWorkDay(workDayDto: WorkDayDto) {
        this.workDayDto = workDayDto;
    }
}