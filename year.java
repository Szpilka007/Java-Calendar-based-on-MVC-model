public class year {
    private int yearNumber;
    private month[] months = new month[12];


    public year(int yearNumber){
        this.yearNumber = yearNumber;
        this.months[0] = new month(31,1);

        if (yearNumber % 4 == 0)
            this.months[1] = new month(28,2);

        else
            this.months[1] = new month(29,2);

        this.months[2] = new month(31,3);
        this.months[3] = new month(30,4);
        this.months[4] = new month(31,5);
        this.months[5] = new month(30,6);
        this.months[6] = new month(31,7);
        this.months[7] = new month(31,8);
        this.months[8] = new month(30,9);
        this.months[9] = new month(31,10);
        this.months[10] = new month(30,11);
        this.months[11] = new month(31,12);

    }

    public int getYearNumber() {
        return yearNumber;
    }
    //public void addEventToMonth(Event ev) { months[]}



}
