export class EventList{
  constructor(
    public eventId:number,
    public eventName :string,
    public description : string,
    public duration : string,
    public location : string,
    public fees : number,
    public tags : string,
    public maxParticipants : number,
    public createdBy : String,
    public registereduser : boolean,
    public userList : any[]
  ){

  }
}