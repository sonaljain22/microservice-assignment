/**
 * 
 */
package com.sample.assignment.transaction.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * @author sonal
 *
 */
@Document(collection="Transaction_Message_Bean")
public class TransactionMessageBean {

	/*
	 * {"OX_TX_NO":"1","X_STTLM_AMT":100,"X_ULTMT_DBTR_CTRY_SUB_DIV":"New York",
	 * "X_PMT_INSTD_AGT_ID":"020010001","X_CTGY_PURP_PRTY":"CONSUMER",
	 * "OX_TTL_INTR_BNK_STTLM_CCY":"USD","X_TX_ID":"0Rz1laMyDnZHy",
	 * "X_ULTMT_DBTR_CTRY":"US"}
	 */

	@NonNull
	@Id
	private String txId;
	private String txNo;
	@NonNull
	private Double sttlmntAmt;
	private String ultmtDbtrCtrySubDiv;
	@NonNull
	private String pmtInstdAgtId;
	private String ctgyPurpPrty;
	@NonNull
	private String ttlIntrBnkSttlmCcy;
	private String ultmtDbtrCtry;

	public String getTxId() {
		return txId;
	}

	@JsonSetter("X_TX_ID")
	public void setTxId(String txId) {
		this.txId = txId;
	}

	public String getTxNo() {
		return txNo;
	}

	@JsonSetter("OX_TX_NO")
	public void setTxNo(String txNo) {
		this.txNo = txNo;
	}

	public Double getSttlmntAmt() {
		return sttlmntAmt;
	}

	@JsonSetter("")
	public void setSttlmntAmt(Double sttlmntAmt) {
		this.sttlmntAmt = sttlmntAmt;
	}

	public String getUltmtDbtrCtrySubDiv() {
		return ultmtDbtrCtrySubDiv;
	}

	@JsonSetter("X_ULTMT_DBTR_CTRY_SUB_DIV")
	public void setUltmtDbtrCtrySubDiv(String ultmtDbtrCtrySubDiv) {
		this.ultmtDbtrCtrySubDiv = ultmtDbtrCtrySubDiv;
	}

	public String getPmtInstdAgtId() {
		return pmtInstdAgtId;
	}

	@JsonSetter("X_PMT_INSTD_AGT_ID")
	public void setPmtInstdAgtId(String pmtInstdAgtId) {
		this.pmtInstdAgtId = pmtInstdAgtId;
	}

	public String getCtgyPurpPrty() {
		return ctgyPurpPrty;
	}

	@JsonSetter("X_CTGY_PURP_PRTY")
	public void setCtgyPurpPrty(String ctgyPurpPrty) {
		this.ctgyPurpPrty = ctgyPurpPrty;
	}

	public String getTtlIntrBnkSttlmCcy() {
		return ttlIntrBnkSttlmCcy;
	}

	@JsonSetter("OX_TTL_INTR_BNK_STTLM_CCY")
	public void setTtlIntrBnkSttlmCcy(String ttlIntrBnkSttlmCcy) {
		this.ttlIntrBnkSttlmCcy = ttlIntrBnkSttlmCcy;
	}

	public String getUltmtDbtrCtry() {
		return ultmtDbtrCtry;
	}

	@JsonSetter("X_ULTMT_DBTR_CTRY")
	public void setUltmtDbtrCtry(String ultmtDbtrCtry) {
		this.ultmtDbtrCtry = ultmtDbtrCtry;
	}
	
	@Override
	public String toString() {
		return "TransactionMessageBean [txId=" + txId + ", txNo=" + txNo + ", sttlmntAmt=" + sttlmntAmt
				+ ", ultmtDbtrCtrySubDiv=" + ultmtDbtrCtrySubDiv + ", pmtInstdAgtId=" + pmtInstdAgtId
				+ ", ctgyPurpPrty=" + ctgyPurpPrty + ", ttlIntrBnkSttlmCcy=" + ttlIntrBnkSttlmCcy + ", ultmtDbtrCtry="
				+ ultmtDbtrCtry + "]";
	}
	
}
