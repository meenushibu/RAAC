/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package brics;
import java.io.Serializable;
/**
 *
 * @author Anju
 */
public class Packet  implements Serializable
{
    private static final long serialVersionUID = 5950169519310163575L;
    private int packetType=0;
    private String packetContent=new String();
    public Packet()
    {
        packetType=0;
        packetContent=new String();
    }
    public Packet(int packetType,String packetContent)
    {
        this.packetType=packetType;
        this.packetContent=packetContent;
    }
    public int getPacketType()
    {
        return this.packetType;
    }
    public String getPacketContent()
    {
        return this.packetContent;
    }
    public void setPacketType(int packetType)
    {
        this.packetType=packetType;
    }
    public void setPacketContent(String packetContent)
    {
        this.packetContent=packetContent;
    }
}
