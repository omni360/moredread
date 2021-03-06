package net.untoldwind.moredread.model.op.triangulator.fist;

/**
 * Original license information:
 * 
 * ----------------------------------------------------------------------
 * 
 * The reference to Fast Industrial Strength Triangulation (FIST) code in this
 * release by Sun Microsystems is related to Sun's rewrite of an early version
 * of FIST. FIST was originally created by Martin Held and Joseph Mitchell at
 * Stony Brook University and is incorporated by Sun under an agreement with The
 * Research Foundation of SUNY (RFSUNY). The current version of FIST is
 * available for commercial use under a license agreement with RFSUNY on behalf
 * of the authors and Stony Brook University. Please contact the Office of
 * Technology Licensing at Stony Brook, phone 631-632-9009, for licensing
 * information.
 * 
 * ----------------------------------------------------------------------
 */

class ListNode {
	int index;
	int prev;
	int next;
	int convex;
	int vcntIndex; // Vertex, Color, Normal, Texture Index

	ListNode(int ind) {
		index = ind;
		prev = -1;
		next = -1;
		convex = 0;
		vcntIndex = -1;
	}

	void setCommonIndex(int comIndex) {
		vcntIndex = comIndex;

	}

	int getCommonIndex() {
		return vcntIndex;
	}
}
