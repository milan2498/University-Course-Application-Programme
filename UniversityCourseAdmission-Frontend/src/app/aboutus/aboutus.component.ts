import { Component, OnInit } from '@angular/core';
import { aboutus } from 'src/model/aboutus';

@Component({
  selector: 'aboutus',
  templateUrl: './aboutus.component.html',
  styleUrls: ['./aboutus.component.css']
})
export class AboutusComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  aboutUs:aboutus[]=[
    {name:'Md Enam',role:'Principal',description:'Capgemini ID: 46059502',mailId:'enam@capgemini.com'},
    {name:'Supratim',role:'Subject Expert',description:'Capgemini ID: 46060201',mailId:'supratim@capgemini.com'},
    {name:'Pritam',role:'Manager',description:'Capgemini ID: 46060215',mailId:'pritam@capgemini.com'},
    {name:'Milan',role:'Recruiter',description:'Capgemini ID: 46059162',mailId:'milan@capgemini.com'},
    {name:'Ayush',role:'Accounts Head',description:'Capgemini ID: 46060216',mailId:'ayush@capgemini.com'},
    {name:'Anirban',role:'Administrator',description:'Capgemini ID: 46060210',mailId:'anirban@capgemini.com'}
  ]

}
