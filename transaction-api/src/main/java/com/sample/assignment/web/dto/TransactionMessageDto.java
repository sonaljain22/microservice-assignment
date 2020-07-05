/**
 * 
 */
package com.sample.assignment.web.dto;

import com.sample.assignment.data.bean.TransactionMessageBean;

/**
 * @author sonal
 *
 */
public class TransactionMessageDto {
	private String txId;
	private String txNo;
	private Double sttlmntAmt;
	private String ultmtDbtrCtrySubDiv;
	private String pmtInstdAgtId;
	private String ctgyPurpPrty;
	private String ttlIntrBnkSttlmCcy;
	private String ultmtDbtrCtry;

	public TransactionMessageDto(TransactionMessageBean entity) {
		this.txId = entity.getTxId();
		this.txNo = entity.getTxNo();
		this.sttlmntAmt = entity.getSttlmntAmt();
		this.ultmtDbtrCtrySubDiv = entity.getUltmtDbtrCtrySubDiv();
		this.pmtInstdAgtId = entity.getPmtInstdAgtId();
		this.ctgyPurpPrty = entity.getCtgyPurpPrty();
		this.ttlIntrBnkSttlmCcy = entity.getTtlIntrBnkSttlmCcy();
		this.ultmtDbtrCtry = entity.getUltmtDbtrCtry();
	}

	public String getTxId() {
		return txId;
	}

	public void setTxId(String txId) {
		this.txId = txId;
	}

	public String getTxNo() {
		return txNo;
	}

	public void setTxNo(String txNo) {
		this.txNo = txNo;
	}

	public Double getSttlmntAmt() {
		return sttlmntAmt;
	}

	public void setSttlmntAmt(Double sttlmntAmt) {
		this.sttlmntAmt = sttlmntAmt;
	}

	public String getUltmtDbtrCtrySubDiv() {
		return ultmtDbtrCtrySubDiv;
	}

	public void setUltmtDbtrCtrySubDiv(String ultmtDbtrCtrySubDiv) {
		this.ultmtDbtrCtrySubDiv = ultmtDbtrCtrySubDiv;
	}

	public String getPmtInstdAgtId() {
		return pmtInstdAgtId;
	}

	public void setPmtInstdAgtId(String pmtInstdAgtId) {
		this.pmtInstdAgtId = pmtInstdAgtId;
	}

	public String getCtgyPurpPrty() {
		return ctgyPurpPrty;
	}

	public void setCtgyPurpPrty(String ctgyPurpPrty) {
		this.ctgyPurpPrty = ctgyPurpPrty;
	}

	public String getTtlIntrBnkSttlmCcy() {
		return ttlIntrBnkSttlmCcy;
	}

	public void setTtlIntrBnkSttlmCcy(String ttlIntrBnkSttlmCcy) {
		this.ttlIntrBnkSttlmCcy = ttlIntrBnkSttlmCcy;
	}

	public String getUltmtDbtrCtry() {
		return ultmtDbtrCtry;
	}

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
