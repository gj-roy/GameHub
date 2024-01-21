export type ApiImage = {
    id:number;
    game:number;
    image_id: string;
    alpha_channel?:boolean;
    animated?:boolean;
    width: number ;
    height: number;
    url:string;
    checksum:string;
};
