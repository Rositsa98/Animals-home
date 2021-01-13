export class PhotoDto{
    photoName: string;
   
    public constructor(photoName?: string){
        this.setPhotoName(photoName);
    }

    public setPhotoName(photoName: string) {
        this.photoName = photoName;
    }

}