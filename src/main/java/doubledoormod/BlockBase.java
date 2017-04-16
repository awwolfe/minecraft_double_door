

package doubledoormod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBase extends Block{// implements ItemModelProvider{
	protected String name;
	
	public BlockBase(Material materialIn,String name){
		
		super(materialIn);
		this.name=name;
		setUnlocalizedName(DoubleDoorMod.MODID+"."+name);
		setRegistryName(name);
		GameRegistry.register(this);
		GameRegistry.register(new ItemBlock(this),getRegistryName());
		
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		
	}
	
	@SideOnly(Side.CLIENT)
	public void initModel(){
		
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(),"inventory"));
	}
	
	@Override 
	public BlockBase setCreativeTab(CreativeTabs tab){
		super.setCreativeTab(tab);
		return this;
	}



}
