package tech.osm8.magicstorage.common.world.data;

import com.zeitheron.hammercore.utils.NBTUtils;
import net.minecraft.nbt.NBTTagCompound;
import org.zeith.terraria.api.entity.IBoss;
import org.zeith.terraria.common.data.world.world.BaseWorldDataTC;
import org.zeith.terraria.common.data.world.world.WorldDataTC;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ShadowDiamondDropData extends BaseWorldDataTC {

    private final Set<String> slainBosses = new HashSet<>();

    public ShadowDiamondDropData(WorldDataTC data) {
        super(data);
    }

    public boolean doesShadowDiamondDrop(IBoss boss) {
        return slainBosses.add(boss.getBossID());
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
//        super.writeToNBT(nbt);
        NBTUtils.writeStringListToNBT(nbt, "slainbosses", new ArrayList<>(slainBosses));
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        slainBosses.clear();
        slainBosses.addAll(NBTUtils.readStringListFromNBT(nbt, "slainbosses"));
    }
}
