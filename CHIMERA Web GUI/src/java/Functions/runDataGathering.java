/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Functions;

/**
 *
 * @author Emerson Chua
 */
public class runDataGathering {
    
    boolean outfileb = false;
    boolean ifaceb = false;
    boolean attackb = false;
    boolean packetfilterb = false;
    boolean packetfilterswitchb = false;
    
    String outfiles = "Output filename is ";
    String ifaces = "Interface is ";
    String attacks = "Attack switch is ";
    String packetfilters = "Packet filter is ";
    String packetfilterswitchs = "Packet filter switch is ";
    
    public runDataGathering(String outputfile, String iface, String attack, String packetfilter, String packetfilterswitch){
        
        System.out.println("Data Gathering ran.");
        
        if(outputfile != null || outputfile != ""){
            outfileb = true;
        }
        if(iface != null || iface != ""){
            ifaceb = true;
        }
        if(attack != null || attack != ""){
            attackb = true;
        }
        if(packetfilter != null || packetfilter != ""){
            packetfilterb = true;
        }
        if(packetfilterswitch != null || packetfilterswitch != ""){
            packetfilterswitchb = true;
        }
        
        if(outfileb)
            System.out.println(outfiles + outputfile);
        
        if(ifaceb)
            System.out.println(ifaces + iface);
        
        if(attackb)
            System.out.println(attacks + attack);
        
        if(packetfilterb)
            System.out.println(packetfilters + packetfilter);
        
        if(packetfilterswitchb)
            System.out.println(packetfilterswitchs + packetfilterswitch);
    }
}
