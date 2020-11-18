package com.instacloud.order2fse.Util;

public class Url {

    private static String Base_Url = "https://api.betsapi.com/v1/bet365/";
    public static String UP_COMING = Base_Url + "upcoming?sport_id=3&token=26747-oAG4SCqB3CcKCn";
    public static String IN_PLAY = Base_Url + "inplay_filter?sport_id=3&token=26747-oAG4SCqB3CcKCn";

    private static String BaseUrl1 = "http://sikandarapi.azurewebsites.net:80/api/";
    private static String BaseUrl2 = "http://sikandarapi.azurewebsites.net:80/";
    public static final String GET_USERS = BaseUrl1 + "UserDetails";
    public static final String BET_BATSMAN = BaseUrl1 + "TopTeamBatsmen";
    public static final String BET_BOWLER = BaseUrl1 + "TopTeamBowlers";
    public static final String BET_MAN_OF_MATCH = BaseUrl1 + "ManoftheMatches";
    public static final String BET_WIN_TOSS = BaseUrl1 + "TossWins";

    public static final String BET_WIN_MATCHES = BaseUrl1 + "ToWinMatches";
    public static final String BET_WIN_MATCH_SIXES = BaseUrl1 + "TotalMatchSixes";

    public static final String BET_PER_OVER_RUNS = BaseUrl1 + "PerOverRuns";
    public static final String BET_MATCH_ODDS = BaseUrl1 + "MatchOdds";
    public static final String BET_ACCOUNTINFO = BaseUrl2 + "GetAccountInfoBUserID";
    public static final String BET_ACCOUNTINFOUpdate = BaseUrl1 + "AccountInfoes";
    public static final String BET_Result_Status = BaseUrl2 + "GetUserResultstatusById";



    public static final String BET_History_MatchOdds = BaseUrl1 + "MatchOdds";
    public static final String BET_History_PerOverRun = BaseUrl1 + "PerOverRuns";
    public static final String BET_History_TopTeamBatsman = BaseUrl1 + "TopTeamBatsmen";
    public static final String BET_History_TopTeamBowler = BaseUrl1 + "TopTeamBowlers";
    public static final String BET_History_ToWinMatches = BaseUrl2 + "GetToWinMatchInfoBUserID";
    public static final String BET_History_TotalMatchSixes = BaseUrl2 + "GetTotalMatchSixInfoByUserID";

}
