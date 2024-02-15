package com.miniProject.FundRiseApp.Service;

import com.miniProject.FundRiseApp.Entity.Donation;

import java.util.List;

public interface DonationService {
    Donation donate(Donation donation);
    List<Donation> viewAllDonations();
    Donation viewDonation(long id);
}
