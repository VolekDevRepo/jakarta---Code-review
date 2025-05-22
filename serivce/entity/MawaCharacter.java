package cz.mawalgar.services.dao.en;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import cz.mawalgar.services.dao.conv.BooleanToIntConverter;
import cz.mawalgar.services.dao.conv.CharClassCodeConverter;
import cz.mawalgar.services.dao.conv.GenderCodeConverter;
import cz.mawalgar.services.dao.conv.MapCodeConverter;
import cz.mawalgar.services.dao.conv.RaceCodeConverter;
import cz.mawalgar.services.val.Gender;
import cz.mawalgar.services.val.Map;
import cz.mawalgar.services.val.Race;
import cz.mawalgar.services.val.CharClass;

/**
 * The persistent class for the core database account.
 */
@Entity
@Table(name = "characters")
public class MawaCharacter implements Serializable {

	private static final long serialVersionUID = 2939592066531503297L;

  @Id
  private int guid;

  @Column
  private Integer account;

  @Column
  private String name;

  @Column(name = "race")
  @Convert(converter = RaceCodeConverter.class)
  private Race race;

  @Column(name = "class")
  @Convert(converter = CharClassCodeConverter.class)
  private CharClass charClass;

  @Column(name = "gender")
  @Convert(converter = GenderCodeConverter.class)
  private Gender gender;

  @Column
  private Integer level;

  @Column
  private Integer xp;

  @Column
  private Integer money;

  @Column
  private Integer skin;

  @Column
  private Integer face;

  @Column
  private Integer hairStyle;

  @Column
  private Integer hairColor;

  @Column
  private Integer facialStyle;

  @Column
  private Integer bankSlots;

  @Column
  private Integer restState;

  @Column
  private Integer playerFlags;

  @Column
  private float position_x;

  @Column
  private float position_y;

  @Column
  private float position_z;

  @Column(name = "map")
  @Convert(converter = MapCodeConverter.class)
  private Map map;

  @Column
  private Integer instance_id;

  @Column
  private Integer instance_mode_mask;

  @Column
  private float orientation;

  @Column
  private String taximask;

  @Column(name = "online")
  @Convert(converter = BooleanToIntConverter.class)
  private boolean online;

  @Column
  private Integer cinematic;

  @Column
  private Integer totaltime;

  @Column
  private Integer leveltime;

  @Column
  private Integer logout_time;

  @Column
  private Integer is_logout_resting;

  @Column
  private float rest_bonus;

  @Column
  private Integer resettalents_cost;

  @Column
  private Integer resettalents_time;

  @Column
  private float trans_x;

  @Column
  private float trans_y;

  @Column
  private float trans_z;

  @Column
  private float trans_o;

  @Column
  private Integer transguid;

  @Column
  private Integer extra_flags;

  @Column
  private Integer stable_slots;

  @Column
  private Integer at_login;

  @Column
  private Integer zone;

  @Column
  private Integer death_expire_time;

  @Column
  private String taxi_path;

  @Column
  private Integer arenaPoints;

  @Column
  private Integer totalHonorPoints;

  @Column
  private Integer todayHonorPoints;

  @Column
  private Integer yesterdayHonorPoints;

  @Column
  private Integer totalKills;

  @Column
  private Integer todayKills;

  @Column
  private Integer yesterdayKills;

  @Column
  private Integer chosenTitle;

  @Column
  private BigInteger knownCurrencies;

  @Column
  private BigInteger watchedFaction;

  @Column
  private Integer drunk;

  @Column
  private Integer health;

  private Integer power1;

  @Column
  private Integer power2;

  @Column
  private Integer power3;

  @Column
  private Integer power4;

  @Column
  private Integer power5;

  @Column
  private Integer power6;

  @Column
  private Integer power7;

  @Column
  private Integer latency;

  @Column
  private Integer talentGroupsCount;

  @Column
  private Integer activeTalentGroup;

  @Column
  private String exploredZones;

  @Column
  private String equipmentCache;

  @Column
  private Integer ammoId;

  @Column
  private String knownTitles;

  @Column
  private Integer actionBars;

  @Column
  private Integer grantableLevels;

  @Column
  private Integer deleteInfos_Account;

  @Column
  private String deleteInfos_Name;

  @Column
  private String deleteDate;

public int getGuid() {
	return guid;
}

public void setGuid(int guid) {
	this.guid = guid;
}

public Integer getAccount() {
	return account;
}

public void setAccount(Integer account) {
	this.account = account;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public Race getRace() {
	return race;
}

public void setRace(Race race) {
	this.race = race;
}

public CharClass getCharClass() {
	return charClass;
}

public void setCharClass(CharClass charClass) {
	this.charClass = charClass;
}

public Gender getGender() {
	return gender;
}

public void setGender(Gender gender) {
	this.gender = gender;
}

public Integer getLevel() {
	return level;
}

public void setLevel(Integer level) {
	this.level = level;
}

public Integer getXp() {
	return xp;
}

public void setXp(Integer xp) {
	this.xp = xp;
}

public Integer getMoney() {
	return money;
}

public void setMoney(Integer money) {
	this.money = money;
}

public Integer getSkin() {
	return skin;
}

public void setSkin(Integer skin) {
	this.skin = skin;
}

public Integer getFace() {
	return face;
}

public void setFace(Integer face) {
	this.face = face;
}

public Integer getHairStyle() {
	return hairStyle;
}

public void setHairStyle(Integer hairStyle) {
	this.hairStyle = hairStyle;
}

public Integer getHairColor() {
	return hairColor;
}

public void setHairColor(Integer hairColor) {
	this.hairColor = hairColor;
}

public Integer getFacialStyle() {
	return facialStyle;
}

public void setFacialStyle(Integer facialStyle) {
	this.facialStyle = facialStyle;
}

public Integer getBankSlots() {
	return bankSlots;
}

public void setBankSlots(Integer bankSlots) {
	this.bankSlots = bankSlots;
}

public Integer getRestState() {
	return restState;
}

public void setRestState(Integer restState) {
	this.restState = restState;
}

public Integer getPlayerFlags() {
	return playerFlags;
}

public void setPlayerFlags(Integer playerFlags) {
	this.playerFlags = playerFlags;
}

public float getPosition_x() {
	return position_x;
}

public void setPosition_x(float position_x) {
	this.position_x = position_x;
}

public float getPosition_y() {
	return position_y;
}

public void setPosition_y(float position_y) {
	this.position_y = position_y;
}

public float getPosition_z() {
	return position_z;
}

public void setPosition_z(float position_z) {
	this.position_z = position_z;
}

public Map getMap() {
	return map;
}

public void setMap(Map map) {
	this.map = map;
}

public Integer getInstance_id() {
	return instance_id;
}

public void setInstance_id(Integer instance_id) {
	this.instance_id = instance_id;
}

public Integer getInstance_mode_mask() {
	return instance_mode_mask;
}

public void setInstance_mode_mask(Integer instance_mode_mask) {
	this.instance_mode_mask = instance_mode_mask;
}

public float getOrientation() {
	return orientation;
}

public void setOrientation(float orientation) {
	this.orientation = orientation;
}

public String getTaximask() {
	return taximask;
}

public void setTaximask(String taximask) {
	this.taximask = taximask;
}

public boolean isOnline() {
	return online;
}

public void setOnline(boolean online) {
	this.online = online;
}

public Integer getCinematic() {
	return cinematic;
}

public void setCinematic(Integer cinematic) {
	this.cinematic = cinematic;
}

public Integer getTotaltime() {
	return totaltime;
}

public void setTotaltime(Integer totaltime) {
	this.totaltime = totaltime;
}

public Integer getLeveltime() {
	return leveltime;
}

public void setLeveltime(Integer leveltime) {
	this.leveltime = leveltime;
}

public Integer getLogout_time() {
	return logout_time;
}

public void setLogout_time(Integer logout_time) {
	this.logout_time = logout_time;
}

public Integer getIs_logout_resting() {
	return is_logout_resting;
}

public void setIs_logout_resting(Integer is_logout_resting) {
	this.is_logout_resting = is_logout_resting;
}

public float getRest_bonus() {
	return rest_bonus;
}

public void setRest_bonus(float rest_bonus) {
	this.rest_bonus = rest_bonus;
}

public Integer getResettalents_cost() {
	return resettalents_cost;
}

public void setResettalents_cost(Integer resettalents_cost) {
	this.resettalents_cost = resettalents_cost;
}

public Integer getResettalents_time() {
	return resettalents_time;
}

public void setResettalents_time(Integer resettalents_time) {
	this.resettalents_time = resettalents_time;
}

public float getTrans_x() {
	return trans_x;
}

public void setTrans_x(float trans_x) {
	this.trans_x = trans_x;
}

public float getTrans_y() {
	return trans_y;
}

public void setTrans_y(float trans_y) {
	this.trans_y = trans_y;
}

public float getTrans_z() {
	return trans_z;
}

public void setTrans_z(float trans_z) {
	this.trans_z = trans_z;
}

public float getTrans_o() {
	return trans_o;
}

public void setTrans_o(float trans_o) {
	this.trans_o = trans_o;
}

public Integer getTransguid() {
	return transguid;
}

public void setTransguid(Integer transguid) {
	this.transguid = transguid;
}

public Integer getExtra_flags() {
	return extra_flags;
}

public void setExtra_flags(Integer extra_flags) {
	this.extra_flags = extra_flags;
}

public Integer getStable_slots() {
	return stable_slots;
}

public void setStable_slots(Integer stable_slots) {
	this.stable_slots = stable_slots;
}

public Integer getAt_login() {
	return at_login;
}

public void setAt_login(Integer at_login) {
	this.at_login = at_login;
}

public Integer getZone() {
	return zone;
}

public void setZone(Integer zone) {
	this.zone = zone;
}

public Integer getDeath_expire_time() {
	return death_expire_time;
}

public void setDeath_expire_time(Integer death_expire_time) {
	this.death_expire_time = death_expire_time;
}

public String getTaxi_path() {
	return taxi_path;
}

public void setTaxi_path(String taxi_path) {
	this.taxi_path = taxi_path;
}

public Integer getArenaPoints() {
	return arenaPoints;
}

public void setArenaPoints(Integer arenaPoints) {
	this.arenaPoints = arenaPoints;
}

public Integer getTotalHonorPoints() {
	return totalHonorPoints;
}

public void setTotalHonorPoints(Integer totalHonorPoints) {
	this.totalHonorPoints = totalHonorPoints;
}

public Integer getTodayHonorPoints() {
	return todayHonorPoints;
}

public void setTodayHonorPoints(Integer todayHonorPoints) {
	this.todayHonorPoints = todayHonorPoints;
}

public Integer getYesterdayHonorPoints() {
	return yesterdayHonorPoints;
}

public void setYesterdayHonorPoints(Integer yesterdayHonorPoints) {
	this.yesterdayHonorPoints = yesterdayHonorPoints;
}

public Integer getTotalKills() {
	return totalKills;
}

public void setTotalKills(Integer totalKills) {
	this.totalKills = totalKills;
}

public Integer getTodayKills() {
	return todayKills;
}

public void setTodayKills(Integer todayKills) {
	this.todayKills = todayKills;
}

public Integer getYesterdayKills() {
	return yesterdayKills;
}

public void setYesterdayKills(Integer yesterdayKills) {
	this.yesterdayKills = yesterdayKills;
}

public Integer getChosenTitle() {
	return chosenTitle;
}

public void setChosenTitle(Integer chosenTitle) {
	this.chosenTitle = chosenTitle;
}

public BigInteger getKnownCurrencies() {
	return knownCurrencies;
}

public void setKnownCurrencies(BigInteger knownCurrencies) {
	this.knownCurrencies = knownCurrencies;
}

public BigInteger getWatchedFaction() {
	return watchedFaction;
}

public void setWatchedFaction(BigInteger watchedFaction) {
	this.watchedFaction = watchedFaction;
}

public Integer getDrunk() {
	return drunk;
}

public void setDrunk(Integer drunk) {
	this.drunk = drunk;
}

public Integer getHealth() {
	return health;
}

public void setHealth(Integer health) {
	this.health = health;
}

public Integer getPower1() {
	return power1;
}

public void setPower1(Integer power1) {
	this.power1 = power1;
}

public Integer getPower2() {
	return power2;
}

public void setPower2(Integer power2) {
	this.power2 = power2;
}

public Integer getPower3() {
	return power3;
}

public void setPower3(Integer power3) {
	this.power3 = power3;
}

public Integer getPower4() {
	return power4;
}

public void setPower4(Integer power4) {
	this.power4 = power4;
}

public Integer getPower5() {
	return power5;
}

public void setPower5(Integer power5) {
	this.power5 = power5;
}

public Integer getPower6() {
	return power6;
}

public void setPower6(Integer power6) {
	this.power6 = power6;
}

public Integer getPower7() {
	return power7;
}

public void setPower7(Integer power7) {
	this.power7 = power7;
}

public Integer getLatency() {
	return latency;
}

public void setLatency(Integer latency) {
	this.latency = latency;
}

public Integer getTalentGroupsCount() {
	return talentGroupsCount;
}

public void setTalentGroupsCount(Integer talentGroupsCount) {
	this.talentGroupsCount = talentGroupsCount;
}

public Integer getActiveTalentGroup() {
	return activeTalentGroup;
}

public void setActiveTalentGroup(Integer activeTalentGroup) {
	this.activeTalentGroup = activeTalentGroup;
}

public String getExploredZones() {
	return exploredZones;
}

public void setExploredZones(String exploredZones) {
	this.exploredZones = exploredZones;
}

public String getEquipmentCache() {
	return equipmentCache;
}

public void setEquipmentCache(String equipmentCache) {
	this.equipmentCache = equipmentCache;
}

public Integer getAmmoId() {
	return ammoId;
}

public void setAmmoId(Integer ammoId) {
	this.ammoId = ammoId;
}

public String getKnownTitles() {
	return knownTitles;
}

public void setKnownTitles(String knownTitles) {
	this.knownTitles = knownTitles;
}

public Integer getActionBars() {
	return actionBars;
}

public void setActionBars(Integer actionBars) {
	this.actionBars = actionBars;
}

public Integer getGrantableLevels() {
	return grantableLevels;
}

public void setGrantableLevels(Integer grantableLevels) {
	this.grantableLevels = grantableLevels;
}

public Integer getDeleteInfos_Account() {
	return deleteInfos_Account;
}

public void setDeleteInfos_Account(Integer deleteInfos_Account) {
	this.deleteInfos_Account = deleteInfos_Account;
}

public String getDeleteInfos_Name() {
	return deleteInfos_Name;
}

public void setDeleteInfos_Name(String deleteInfos_Name) {
	this.deleteInfos_Name = deleteInfos_Name;
}

public String getDeleteDate() {
	return deleteDate;
}

public void setDeleteDate(String deleteDate) {
	this.deleteDate = deleteDate;
}

}