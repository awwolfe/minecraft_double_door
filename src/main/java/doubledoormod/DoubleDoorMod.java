package doubledoormod;



import doubledoormod.proxies.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = DoubleDoorMod.MODID, version = DoubleDoorMod.VERSION, name=DoubleDoorMod.NAME)
public class DoubleDoorMod
{
    public static final String MODID = "doubledoormod";
    public static final String VERSION = "1.0";
    public static final String NAME= "Double Door Mod";
    @SidedProxy(serverSide="doubledoormod.proxies.CommonProxy",clientSide="doubledoormod.proxies.ClientProxy")
	public static CommonProxy proxy;
    
    @Mod.Instance(MODID)
	public static DoubleDoorMod instance;
    
    
    @Mod.EventHandler
	public void preInit(FMLPreInitializationEvent even){
    	//System.out.println(DoubleDoorMod.NAME+" loading......");
    	ModBlocks.init();
    	
    	proxy.preInit(even);
    }
    
    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
    	
    		
    }
}
