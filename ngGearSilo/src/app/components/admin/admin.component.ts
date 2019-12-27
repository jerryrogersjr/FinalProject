import { Router } from "@angular/router";
import { AuthService } from "src/app/services/auth.service";
import { Reservation } from "./../../models/reservation";
import { GearService } from "src/app/services/gear.service";
import { UserService } from "./../../services/user.service";
import { Component, OnInit } from "@angular/core";
import { User } from "src/app/models/user";
import { Gear } from "src/app/models/gear";
import { ReservationService } from "src/app/services/reservation.service";

@Component({
  selector: "app-admin",
  templateUrl: "./admin.component.html",
  styleUrls: ["./admin.component.css"]
})
export class AdminComponent implements OnInit {
  users: User[] = [];
  selectedUser: User = null;
  user: User = null;
  updateUser: User = null;
  disableUser: User = null;

  gearList: Gear[] = [];
  gear: Gear = null;
  selectedGear: Gear = null;

  resvList: Reservation[] = [];
  resv: Reservation = null;
  selectedResv: Reservation = null;

  // admin: User = null;

  constructor(
    private userSvc: UserService,
    private gearSvc: GearService,
    private resvSvc: ReservationService,
    private authSvc: AuthService,
    private router: Router
  ) {}

  ngOnInit() {
    this.loadUsers();
    this.loadGear();
    this.loadReservations();
    // this.adminLoggedInCheck();
  }

  // Admin Check here not good
  // adminLoggedInCheck() {
  //   this.authSvc.getCredentials();
  //   if (this.selectedUser.role !== "admin") {
  //     return this.router.navigateByUrl("#/home");
  //   } else {
  //     this.loadUsers();
  //     this.loadGear();
  //     this.loadReservations();
  //   }
  // }

  // Users

  public loadUsers() {
    const userList: [] = [];

    this.userSvc.index().subscribe(
      good => {
        console.log(good);
        this.users = good;
      },
      bad => {
        console.log(bad);
      }
    );
  }

  public countUsers() {
    return this.users.length;
  }

  public countActiveU() {
    // set data aggr. for active users
  }

  public setUpdateUser() {
    this.updateUser = Object.assign({}, this.selectedUser);
  }

  public updatedUser(user: User) {
    this.userSvc.update(user).subscribe(
      uData => {
        this.loadUsers();
        this.selectedUser = null;
        this.updatedUser = null;
      },
      uErr => {
        this.loadUsers();
        console.error("updatedUser: Error");
        console.error(uErr);
      }
    );
  }

  // Gear

  public loadGear() {
    // this.clearSearch();
    this.gearSvc.index().subscribe(
      gData => {
        console.log(gData);
        this.gearList = gData;
      },
      didntWork => {
        console.log(didntWork);
      }
    );
  }

  public displayGearItem(gear: Gear) {
    this.selectedGear = gear;
  }

  public countGear() {
    return this.gearList.length;
    // Add data aggr. for active count.
  }

  // RESERVATIONS

  public loadReservations() {
    this.resvSvc.index().subscribe(
      rData => {
        console.log(rData);
        this.resvList = rData;
      },
      rErr => {
        console.log(rErr);
      }
    );
  }

  public countResv() {
    return this.resvList.length;
  }
}
