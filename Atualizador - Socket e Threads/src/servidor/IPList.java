package servidor;

import java.util.ArrayList;

public class IPList
{
    private ArrayList<String> IPs;

    IPList()
    {
        IPs = new ArrayList<String>();
    }

    private ArrayList<String> getIPs()
    {
        return this.IPs;
    }

    public void setIPs(ArrayList<String> IPs)
    {
        this.IPs = IPs;
    }

    boolean addIP(String IP)
    {
        boolean flag = false;
        for (String IP1 : this.IPs) {
            if (IP1.equals(IP)) {
                flag = true;
                break;
            }
        }
        if(flag)
        {
            return false;
        }
        else
        {
            this.IPs.add(IP);
            return true;
        }
    }

    public void resetIPs()
    {
        this.IPs = new ArrayList<String>();
    }

    public boolean removeByIP(String IP)
    {
        boolean flag = false;
        int i;
        for(i = 0; i < this.IPs.size(); i++)
        {
            if(this.IPs.get(i).equals(IP))
            {
                flag = true;
                break;
            }
        }
        if(flag)
        {
            this.IPs.remove(i);
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean removeByIndex(int index)
    {
        if(index > this.IPs.size() || index < 1)
        {
            return false;
        }
        else
        {
            this.IPs.remove(index);
            return true;
        }
    }

    String getIP(int index)
    {
        return IPs.get(index);
    }

    int size()
    {
        return IPs.size();
    }

    public String toString()
    {
        StringBuilder sReturn = new StringBuilder("Lista de IPs: \n\n");
        for(int i = 0; i < this.IPs.size(); i++)
        {
            sReturn.append(i + 1).append(" - ").append(this.IPs.get(i)).append("\n");
        }
        return sReturn.toString();
    }


    public boolean equals(IPList IPL)
    {
        return IPL.getIPs().equals(this.IPs);
    }

}
