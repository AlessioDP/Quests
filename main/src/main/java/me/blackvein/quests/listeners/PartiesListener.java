/*******************************************************************************************************
 * Continued by PikaMug (formerly HappyPikachu) with permission from _Blackvein_. All rights reserved.
 * 
 * THIS SOFTWARE IS PROVIDED "AS IS" AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN
 * NO EVENT SHALL THE REGENTS OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY
 * OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *******************************************************************************************************/

package me.blackvein.quests.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.alessiodp.parties.api.events.bukkit.party.BukkitPartiesPartyPostCreateEvent;
import com.alessiodp.parties.api.events.bukkit.party.BukkitPartiesPartyPostDeleteEvent;
import com.alessiodp.parties.api.events.bukkit.player.BukkitPartiesPlayerPostJoinEvent;
import com.alessiodp.parties.api.events.bukkit.player.BukkitPartiesPlayerPostLeaveEvent;

import me.blackvein.quests.util.Lang;

public class PartiesListener implements Listener {
    
    @EventHandler
    public void onPartyCreate(final BukkitPartiesPartyPostCreateEvent event) {
        if (event.getCreator() != null) {
            final Player p = Bukkit.getServer().getPlayer(event.getCreator().getPlayerUUID());
            if (p != null) {
                if (Lang.get("questPartiesCreate").length() > 0) {
                    p.sendMessage(ChatColor.YELLOW + Lang.get("questPartiesCreate"));
                }
            }
        }
    }
    
    @EventHandler
    public void onPartyDeleteEvent(final BukkitPartiesPartyPostDeleteEvent event) {
        if (event.getCommandSender() != null) {
            final Player p = Bukkit.getServer().getPlayer(event.getCommandSender().getPlayerUUID());
            if (p != null) {
                if (Lang.get("questPartiesDelete").length() > 0) {
                    p.sendMessage(ChatColor.RED + Lang.get("questPartiesDelete"));
                }
            }
        }
    }
    
    @EventHandler
    public void onPlayerJoinEvent(final BukkitPartiesPlayerPostJoinEvent event) {
        if (event.isInvited() && event.getInviter() != null) {
            final Player i = Bukkit.getServer().getPlayer(event.getInviter());
            if (i != null) {
                if (Lang.get("questPartiesInvite").length() > 0) {
                    i.sendMessage(ChatColor.GREEN + Lang.get(i, "questPartiesInvite").replace("<player>", i.getName()));
                }
            }
        }
        final Player p = Bukkit.getServer().getPlayer(event.getPartyPlayer().getPlayerUUID());
        if (p != null) {
            if (Lang.get("questPartiesJoin").length() > 0) {
                p.sendMessage(ChatColor.GREEN + Lang.get(p, "questPartiesJoin").replace("<player>", p.getName()));
            }
        }
    }
    
    @EventHandler
    public void onPlayerLeaveEvent(final BukkitPartiesPlayerPostLeaveEvent event) {
        if (event.isKicked() && event.getKicker() != null) {
            final Player k = Bukkit.getServer().getPlayer(event.getKicker().getPlayerUUID());
            if (k != null) {
                if (Lang.get("questPartiesKicked").length() > 0) {
                    k.sendMessage(ChatColor.RED + Lang.get(k, "questPartiesKicked").replace("<player>", k.getName()));
                }
            }
        }
        final Player p = Bukkit.getServer().getPlayer(event.getPartyPlayer().getPlayerUUID());
        if (p != null) {
            if (Lang.get("questPartiesLeave").length() > 0) {
                p.sendMessage(ChatColor.RED + Lang.get(p, "questPartiesLeave").replace("<player>", p.getName()));
            }
        }
    }
}
