package Repository.Model;


/*AppInfo*/
public class AppInfo {
    public int uid;
    public String appName;
    public String packageName;
    public String label;
    public int icon;

    public boolean system;
    public boolean enabled;
    public boolean persistent;


    public AppInfo(String appName, String packageName, int icon) {
        this.appName = appName;
        this.packageName = packageName;
        this.icon = icon;
    }
}

