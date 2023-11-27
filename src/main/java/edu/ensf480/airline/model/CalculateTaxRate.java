package edu.ensf480.airline.model;

/**
 * Tax enum for the Airline Reservation System
 *
 * This class is used to determine PST based on province and to get the GST value
 *
 * @version 1.0
 * @since 2023-11-26
 */
public enum CalculateTaxRate {
    AB {public double getPST() {return 0.00;}
        public String toString(){return "Alberta";}},
    BC {public double getPST() {return 0.07;}
        public String toString(){return "British Columbia";}},
    MB {public double getPST() {return 0.07;}
        public String toString(){return "Manitoba";}},
    NB {public double getPST() {return 0.10;}
        public String toString(){return "New Brunswick";}},
    NL {public double getPST() {return 0.10;}
        public String toString(){return "Newfoundland and Labrador";}},
    NS {public double getPST() {return 0.10;}
        public String toString(){return "Nova Scotia";}},
    ON {public double getPST() {return 0.08;}
        public String toString(){return "Ontario";}},
    PE {public double getPST() {return 0.10;}
        public String toString(){return "Prince Edward Island";}},
    QC {public double getPST() {return 0.10;}
        public String toString(){return "Quebec";}},
    SK {public double getPST() {return 0.06;}
        public String toString(){return "Saskatchewan";}};

    private static final double GST= 0.05;

    public static double getGST(){return GST;}
    public abstract double getPST();
    public abstract String toString();
}