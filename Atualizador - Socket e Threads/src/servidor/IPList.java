package servidor;

import java.util.ArrayList;

public class IPList
{
    ArrayList<String> IPs;

    public IPList()
    {
        IPs = new ArrayList<String>();
    }

    public ArrayList<String> getIPs()
    {
        return this.IPs;
    }

    public void setIPs(ArrayList<String> IPs)
    {
        this.IPs = IPs;
    }

    public boolean addIP(String IP)
    {
        boolean flag = false;
        for(int i = 0; i < this.IPs.size(); i++)
        {
            if(this.IPs.get(i).equals(IP))
            {
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

    public String getIP(int index)
    {
        return IPs.get(index);
    }

    public int size()
    {
        return IPs.size();
    }

    public String toString()
    {
        String sReturn = "Lista de IPs: \n\n";
        for(int i = 0; i < this.IPs.size(); i++)
        {
            sReturn += (i+1) + " - " + this.IPs.get(i) + "\n";
        }
        return sReturn;
    }


    public boolean equals(IPList IPL)
    {
        if(IPL.getIPs().equals(this.IPs))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}
