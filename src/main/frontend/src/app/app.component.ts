import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {NavigationEnd, Router} from "@angular/router";
import {SseClient} from 'angular-sse-client';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit {

  navSub;
  title = 'Chess';

  gameStarted: boolean;
  playingBlack: boolean;
  gameMessage: string;
  board: string[][];
  fieldClass: string[][];
  color: string;
  whitePromoting: boolean;
  blackPromoting: boolean;

  constructor(
    private http: HttpClient,
    private router: Router,
    private sseClient: SseClient) {
    this.navSub = this.router.events.subscribe((e: any) => {
      if (e instanceof NavigationEnd) {
      }
    })
  }

  ngOnInit() {
    this.hasGameStarted();
    this.getFieldClasses();
    this.getPieces();
    this.getPlayerColor();
    this.getGameMessage();
    this.isWhitePromoting();
    this.isBlackPromoting();
    this.subscribeToSSE();
  }

  subscribeToSSE() {
    this.sseClient.get('http://127.0.0.1:81/subscribe')
      .subscribe(data => {
        this.hasGameStarted();
        this.getFieldClasses();
        this.getPieces();
        this.getPlayerColor();
        this.getGameMessage();
        this.isWhitePromoting();
        this.isBlackPromoting();
      });
  }

  getPieces() {
    this.http
      .get<string[][]>('http://127.0.0.1:81/api/board')
      .subscribe(data => {
        this.board = data;
      });
  }

  getFieldClasses() {
    this.http
      .get<string[][]>('http://127.0.0.1:81/api/fieldClass')
      .subscribe(data => {
        this.fieldClass = data;
      });
  }

  getPlayerColor() {
    this.http
      .get<boolean>('http://127.0.0.1:81/api/playingBlack')
      .subscribe(data => {
        this.playingBlack = data;
      });
  }

  isWhitePromoting() {
    this.http
      .get<boolean>('http://127.0.0.1:81/api/whitePromoting')
      .subscribe(data => {
        this.whitePromoting = data;
      });
  }

  isBlackPromoting() {
    this.http
      .get<boolean>('http://127.0.0.1:81/api/blackPromoting')
      .subscribe(data => {
        this.blackPromoting = data;
      });
  }

  promote(piece: number) {
    this.http
      .post<string>('http://127.0.0.1:81/api/promote/' + piece, null)
      .subscribe(() => {
      });
  }

  getGameMessage() {
    this.http
      .get<string>('http://127.0.0.1:81/api/gameMessage')
      .subscribe(response => {
        this.gameMessage = response;
      });
  }

  pickColor(color: string) {
    this.http
      .post<string>('http://127.0.0.1:81/api/' + color, null)
      .subscribe(() => {
        window.location.reload();
      });
  }

  resetBoard() {
    this.http
      .post<string>('http://127.0.0.1:81/api/' + 'reset/', null)
      .subscribe(() => {
        window.location.reload();
      });
  }

  select(row: number, col: number) {
    this.http
      .post<string>('http://127.0.0.1:81/api/' + row + '/' + col, null)
      .subscribe(() => {
      });
  }

  hasGameStarted() {
    this.http
      .get<boolean>('http://127.0.0.1:81/api/gameStarted')
      .subscribe(data => {
        this.gameStarted = data;
      });
  }

}
